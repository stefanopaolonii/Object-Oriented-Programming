package utility;
/**
 * Represents a point in 2D coordinate system
 * 
 */
public class Point {
     /**
         * The longitude of this {@code Point}.
         */
        public final double lon;

        /**
         * The latitude of this {@code Point}.
         */
        public final double lat;

        /**
         * Constructs and initializes a {@code Point} with the
         * specified coordinates.
         *
         * @param lon the longitude of the newly
         *          constructed {@code Point}
         * @param lat the latitude of the newly
         *          constructed {@code Point}
         */
        public Point(double lon, double lat) {
            this.lon = lon;
            this.lat = lat;
        }

        /**
         * retrieves the longitude
         * @return the longitude
         */
        public double getLon() {
            return lon;
        }

        /**
         * Retrieves the latitude
         * @return the latitude
         */
        public double getLat() {
            return lat;
        }

        /**
         * Returns a {@code String} that represents the value
         * of this {@code Point}.
         * @return a string representation of this {@code Point}.
         */
        public String toString() {
            return "Point("+lon+", "+lat+")";
        }

}
