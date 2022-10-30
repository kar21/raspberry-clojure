
; initial state when machine turned on
(def state
  (atom {:enabled false
         :valves [0 0 0 0 0 0 0 0]}))

; test our stateß
@state
(:enabled @state)
(if (:enabled @state) "sistem its running" "all valves closed")

; set enabled to true
(swap! state assoc :enabled true)

; set enabled to false
(swap! state assoc :enabled false)


