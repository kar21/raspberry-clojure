(ns water.state
  )

; initial state when machine turned on
(def state
  (atom {:enabled false
         :valves [0 0 0 0 0 0 0 0]}))

(defn set-enabled [b]
  (swap! state assoc :enabled b))

(defn toggle-enabled []
  (swap! state update-in [:enabled] not))

(defn set-valve-level [i l]
  (swap! state assoc-in [:valves i] l))

(defn set-valves-level [l]
  (for [i (range 8)]
    (set-valve-level i l)))

(comment
(set-valves-level 0)
(set-valves-level 3)
(set-valves-level 1)  

  ;
  )


(defn level-open-at-min [l m]
  (cond
    (> m 10) false
    (and (>= l 5) (<= m 5)) true
    (and (>= l 4) (<= m 4)) true
    (and (>= l 3) (<= m 3)) true
    (and (>= l 2) (<= m 2)) true
    (and (>= l 1) (<= m 1)) true
    (and (> l 0) (== m 0)) true
    :else false))

(comment
  ; at biggest level, it should be open up to 5 minutes
  (level-open-at-min 5 2)
  (level-open-at-min 5 5)
  (level-open-at-min 5 6)

  ; at any level (bigger than 0) it should be open at 0 minutes
  (level-open-at-min 1 0)
  (level-open-at-min 4 0)
  (level-open-at-min 0 0)

;  
  )


(defn open-levels-at-min [m]
  (let [open (map #(level-open-at-min % m) (:valves @state))]
    (swap! state assoc-in [:current] {:minute m :open open})
    )
  
  )

(comment 
  (open-levels-at-min 1)  
  (open-levels-at-min 4)
  (open-levels-at-min 5)
  (open-levels-at-min 6)
  (open-levels-at-min 20)
;  
  )

(comment
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

  (set-valve-level 0 5)
  (set-valve-level 3 5)
  (set-valve-level 7 2)

  ;
  )







