

(def state
  (atom {:enabled false
         :valves [0 0 0 0 0 0 0 0]}))


(:enabled @state)
(:valves @state)

(get (:valves @state) 3)

(assoc [0 0 0 0] 2 4)
<<<<<<< HEAD


(assoc (:valves @state) 2 4)
(assoc @state
       :valves (0 0 0 0))


(defn set-valves-level)[]
=======
(assoc (:valves @state) 2 4)


(assoc @state
       :valves
       (assoc (:valves @state) 2 4))

(reset! state (assoc @state
                     :valves
                     (assoc (:valves @state) 2 4)))

@state

; one vALVE

(defn set-valve-level [i l]
  (println "set valve level number" i " to " l)
  (reset! state (assoc @state
                       :valves
                       (assoc (:valves @state) i l))))

(set-valve-level 6 3)
(set-valve-level 5 2)

; ALL VALVES

(for [i (range 8)]
  (set-valve-level i 3))

(defn set-valves-level [l]
  (for [i (range 8)]
    (set-valve-level i l)))


(set-valves-level 0)
(set-valves-level 3)
(set-valves-level 1)
>>>>>>> 03d1a0d83b4b2c0c2b601ba7009146779b4d62f5
