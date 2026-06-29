package org.osmdroid.views.util;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import java.util.Iterator;
import java.util.List;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.PointL;
import org.osmdroid.util.TileSystem;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;

@Deprecated
/* loaded from: classes3.dex */
public class PathProjection {
    public static Path toPixels(Projection projection, List<? extends GeoPoint> list, Path path) {
        return toPixels(projection, list, path, true);
    }

    public static Path toPixels(Projection projection, List<? extends GeoPoint> list, Path path, boolean z) throws IllegalArgumentException {
        PointF relativePositionOfGeoPointInBoundingBoxWithLinearInterpolation;
        if (list.size() < 2) {
            throw new IllegalArgumentException("List of GeoPoints needs to be at least 2.");
        }
        Path path2 = path != null ? path : new Path();
        path2.incReserve(list.size());
        TileSystem tileSystem = MapView.getTileSystem();
        Iterator<? extends GeoPoint> it = list.iterator();
        boolean z2 = true;
        while (it.hasNext()) {
            GeoPoint next = it.next();
            Point point = new Point();
            double dMapSize = TileSystem.MapSize(projection.getZoomLevel());
            PointL mercatorFromGeo = tileSystem.getMercatorFromGeo(next.getLatitude(), next.getLongitude(), dMapSize, null, true);
            point.x = projection.getTileFromMercator(mercatorFromGeo.x);
            point.y = projection.getTileFromMercator(mercatorFromGeo.y);
            PointL pointL = new PointL(projection.getMercatorFromTile(point.x), projection.getMercatorFromTile(point.y));
            PointL pointL2 = new PointL(projection.getMercatorFromTile(point.x + TileSystem.getTileSize()), projection.getMercatorFromTile(point.y + TileSystem.getTileSize()));
            Iterator<? extends GeoPoint> it2 = it;
            GeoPoint geoFromMercator = tileSystem.getGeoFromMercator(pointL.x, pointL.y, dMapSize, null, true, true);
            GeoPoint geoFromMercator2 = tileSystem.getGeoFromMercator(pointL2.x, pointL2.y, dMapSize, null, true, true);
            BoundingBox boundingBox = new BoundingBox(geoFromMercator.getLatitude(), geoFromMercator.getLongitude(), geoFromMercator2.getLatitude(), geoFromMercator2.getLongitude());
            if (z && projection.getZoomLevel() < 7.0d) {
                relativePositionOfGeoPointInBoundingBoxWithLinearInterpolation = boundingBox.getRelativePositionOfGeoPointInBoundingBoxWithExactGudermannInterpolation(next.getLatitude(), next.getLongitude(), null);
            } else {
                relativePositionOfGeoPointInBoundingBoxWithLinearInterpolation = boundingBox.getRelativePositionOfGeoPointInBoundingBoxWithLinearInterpolation(next.getLatitude(), next.getLongitude(), null);
            }
            Rect screenRect = projection.getScreenRect();
            Point point2 = new Point(projection.getTileFromMercator(screenRect.centerX()), projection.getTileFromMercator(screenRect.centerY()));
            PointL pointL3 = new PointL(projection.getMercatorFromTile(point2.x), projection.getMercatorFromTile(point2.y));
            int i = point2.x - point.x;
            int i2 = point2.y - point.y;
            long tileSize = pointL3.x - (TileSystem.getTileSize() * i);
            long tileSize2 = pointL3.y - (TileSystem.getTileSize() * i2);
            long tileSize3 = tileSize + ((long) (relativePositionOfGeoPointInBoundingBoxWithLinearInterpolation.x * TileSystem.getTileSize()));
            long tileSize4 = tileSize2 + ((long) (relativePositionOfGeoPointInBoundingBoxWithLinearInterpolation.y * TileSystem.getTileSize()));
            if (z2) {
                path2.moveTo(tileSize3, tileSize4);
            } else {
                path2.lineTo(tileSize3, tileSize4);
            }
            z2 = false;
            it = it2;
        }
        return path2;
    }
}
