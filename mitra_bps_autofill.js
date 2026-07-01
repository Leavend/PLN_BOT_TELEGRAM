// ============================================================
// BPS MITRA REGISTRATION - Auto-Fill Scripts
// Buka halaman di browser biasa, tekan F12 → Console → paste
// ============================================================

// ┌──────────────────────────────────────────────────────────┐
// │ STEP 1: Paste ini di https://mitra.bps.go.id/registrasi-with-code
// └──────────────────────────────────────────────────────────┘

// --- COPY DARI SINI (STEP 1) ---
(function(){
  const CODE = "PLN26";
  const EMAIL = "huanam.a.si12.3@gmail.com";
  const inputs = document.querySelectorAll('input');
  inputs.forEach(i => {
    const p = (i.placeholder||'').toLowerCase();
    const n = (i.name||'').toLowerCase();
    const t = (i.type||'').toLowerCase();
    if(p.includes('kode') || n.includes('code') || n==='code') {
      i.focus(); i.value = CODE;
      i.dispatchEvent(new Event('input',{bubbles:true}));
      console.log('✅ Kode diisi: '+CODE);
    }
    if(p.includes('email') || p.includes('mail') || n.includes('email') || t==='email') {
      i.focus(); i.value = EMAIL;
      i.dispatchEvent(new Event('input',{bubbles:true}));
      console.log('✅ Email diisi: '+EMAIL);
    }
  });
  alert('✅ Kode & Email terisi!\n\nSekarang solve CAPTCHA lalu klik tombol Cek/Submit.');
})();
// --- END STEP 1 ---


// ┌──────────────────────────────────────────────────────────┐
// │ STEP 2: Paste ini di https://mitra.bps.go.id/registrasi
// └──────────────────────────────────────────────────────────┘

// --- COPY DARI SINI (STEP 2) ---
(function(){
  const NIK   = "6474010508010008";
  const NAMA  = "Akbar Bagas Kara";
  const EMAIL = "huanam.a.si12.3@gmail.com";
  const PASS  = "Pln@1234";

  function fill(input, val) {
    input.focus();
    // React/Vue reactivity hack
    const nativeSet = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype,'value').set;
    nativeSet.call(input, val);
    input.dispatchEvent(new Event('input',{bubbles:true}));
    input.dispatchEvent(new Event('change',{bubbles:true}));
  }

  const inputs = document.querySelectorAll('input');
  let pwdCount = 0;
  inputs.forEach(i => {
    const p = (i.placeholder||'').toLowerCase();
    const n = (i.name||'').toLowerCase();
    const t = (i.type||'').toLowerCase();
    const l = (i.labels&&i.labels[0]?i.labels[0].textContent:'').toLowerCase();
    const all = p+' '+n+' '+l;

    if(all.match(/nik|induk/)) { fill(i, NIK); console.log('✅ NIK'); }
    else if(all.match(/nama|name/) && !all.match(/user/)) { fill(i, NAMA); console.log('✅ Nama'); }
    else if(t==='email' || all.match(/email|mail/)) { fill(i, EMAIL); console.log('✅ Email'); }
    else if(t==='password' || all.match(/password|sandi/)) {
      fill(i, PASS);
      pwdCount++;
      console.log('✅ Password '+(pwdCount>1?'(confirm)':''));
    }
  });
  alert('✅ Form terisi!\n\nNIK: '+NIK+'\nNama: '+NAMA+'\nEmail: '+EMAIL+'\n\nSolve CAPTCHA lalu klik Daftar.');
})();
// --- END STEP 2 ---
