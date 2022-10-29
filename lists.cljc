(require ['demo.movies :refer [movies]])


(count movies)

(clojure.pprint/print-table movies)

