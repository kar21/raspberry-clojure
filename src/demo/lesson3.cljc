
; control structure

(def rainy? false)

(if rainy?
  "Panama - always rain :-("
  "Sunshine .. beach time!")

(def status 3)

(case status
  0 "all systems working normally"
  1 "night time (no watering)"
  2 "day time watering"
  3 "day time (no watering)"
  "what the fuck")

;; Used Thread/sleep to simulate long running process
(def what-is-the-answer-to-life
    (future 
        (println "[Future] started computation")
        (Thread/sleep 3000) ;; running for 3 seconds
        (println "[Future] completed computation")
        42))



