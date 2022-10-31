(ns water.time)

(defn now [] 
  (java.util.Date.))

(defn now-unix []
 (-> (java.util.Date.)
      (.getTime)))

(defn current-hour []
  (-> (java.time.LocalDateTime/now)
      (.getHour)))

(defn current-minute []
  (-> (java.time.LocalDateTime/now)
      (.getMinute)))

(comment
  (now)
  (now-unix)
  (current-hour)  
  ;
)



