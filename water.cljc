

(def state
  (atom {:enabled false
         :valves [0 0 0 0 0 0 0 0]}))


(:enabled @state)
(:valves @state)

(get (:valves @state) 3)

