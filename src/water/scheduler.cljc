(ns water.scheduler
  (:require
   [water.time]
   [water.state]
   [water.display]))


(defn start-valve-scheduler []
  (future
    (loop []
      (let [m (water.time/current-minute)]
        (println "managing valves for minute: " m)
        (water.state/open-levels-at-min m)
        (water.display/print-state)
        (Thread/sleep 30000))

      (recur)))
  nil ; return nil, otherwise in terminal it will show an error.
  )


(comment
  (start-valve-scheduler)
  ;
  )

(defn start-simulator []
  (future
    (loop []
      (println "toggling enabled state")
      (water.state/toggle-enabled)
      (water.display/print-state)
      (Thread/sleep 10000)
      (recur)))
  nil ; return nil, otherwise in terminal it will show an error.
  )

(comment
  (start-simulator)

  ;
  )