

(require '[babashka.curl :as curl])
(require '[cheshire.core :as json])

(curl/get "https://httpstat.us/200")

(def api-key "k_7pcj0max")

(def url-movies 
  (str "https://imdb-api.com/en/API/MostPopularMovies/" api-key)
  )

(-> (curl/get url-movies)
    :body
    (json/parse-string true)
    :items)


