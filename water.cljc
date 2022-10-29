

(def state
  (atom {:enabled false
         :valves [0 0 0 0 0 0 0 0]}))


(:enabled @state)
(:valves @state)

(get (:valves @state) 3)

(assoc [0 0 0 0] 2 4)


(assoc (:valves @state) 2 4)
(assoc @state
       :valves (0 0 0 0))


(defn set-valves-level)[]