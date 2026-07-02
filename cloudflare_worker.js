/**
 * Cloudflare Worker: Secure Reverse Proxy Gateway for BPS API & Keycloak SSO.
 * 
 * Instructions:
 * 1. Go to your Cloudflare Dashboard -> Workers & Pages.
 * 2. Create a new Worker (e.g., named "bps-proxy-tunnel").
 * 3. Copy and paste this code into the Worker editor.
 * 4. Save and deploy.
 * 5. Copy the deployed worker URL (e.g., https://bps-proxy-tunnel.your-subdomain.workers.dev)
 *    and paste it in your `.env` file as `CLOUDFLARE_PROXY_URL`.
 */

addEventListener('fetch', event => {
  event.respondWith(handleRequest(event.request))
})

async function handleRequest(request) {
  // 1. Get the original target URL from the custom header
  const targetUrl = request.headers.get('x-target-url')
  if (!targetUrl) {
    return new Response(
      JSON.stringify({
        success: false,
        message: "Missing x-target-url header. Cloudflare Worker proxy is active but target was not specified."
      }),
      {
        status: 400,
        headers: { 'Content-Type': 'application/json' }
      }
    )
  }

  // 2. Clone headers and remove CF-specific or proxy-specific headers
  const headers = new Headers(request.headers)
  headers.delete('x-target-url')
  headers.delete('host') // Let fetch handle the host header correctly

  // 3. Build fetch initiation options
  const init = {
    method: request.method,
    headers: headers,
    redirect: 'manual' // Handled by Python client's session
  }

  // 4. Attach request body if present
  if (['POST', 'PUT', 'PATCH', 'DELETE'].includes(request.method)) {
    init.body = await request.blob()
  }

  try {
    // 5. Perform request forwarding to BPS
    const response = await fetch(targetUrl, init)
    
    // 6. Return response back to Python client
    return response
  } catch (err) {
    return new Response(
      JSON.stringify({
        success: false,
        message: `Cloudflare Tunnel Proxy Error: ${err.message}`
      }),
      {
        status: 502,
        headers: { 'Content-Type': 'application/json' }
      }
    )
  }
}
