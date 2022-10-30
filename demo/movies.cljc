(ns demo.movies)

(require '[babashka.curl :as curl])
(require '[cheshire.core :as json])

; https://imdb-api.com/API

(def api-key "k_7pcj0max")

(def url-movies
  (str "https://imdb-api.com/en/API/MostPopularMovies/" api-key))


(defn get-movies []
(-> (curl/get url-movies)
    :body
    (json/parse-string true)
    :items))

(def movies (get-movies))


(comment 
  (curl/get "https://httpstat.us/200")  

movies
  ;
  )






