(require ['demo.movies :refer [movies]])



(slurp "/Users/Josephy/Documents/raspberry-clojure/movies.edn")

(def m (slurp "/Users/Josephy/Documents/raspberry-clojure/movies.edn"))

m
(type m)
(clojure.edn/read-string m)

(def movies (clojure.edn/read-string m))

(type movies)

(get movies 0)
(last movies)
(type (last movies))
(keys (last movies))
(count movies)
(map :title movies)
(count (map :title movies))
(clojure.pprint/print-table [:title :year] movies)
(defn -ilikeit [m]
(= "2021" (:year m)))
(-ilikeit {:year 1999})
(-ilikeit {:year 2021})
(filter -ilikeit movies)
(clojure.pprint/print-table [:title :imDbRating] movies)
