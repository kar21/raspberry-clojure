
; initial state when machine turned on
(def state
  (atom {:enabled false
         :valves [0 0 0 0 0 0 0 0]}))

; test our state
@state
(:enabled @state)
(if (:enabled @state) "sistem its running" "all valves closed")

; set enabled to true
(swap! state assoc :enabled true)

; set enabled to false
(swap! state assoc :enabled false)

; toggle state
(swap! state update-in [:enabled] not)


(future
  (loop []
   (println "toggling state")
    (swap! state update-in [:enabled] not)
    (Thread/sleep 10000)
    (recur)))


