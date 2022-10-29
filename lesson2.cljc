
; control structure

(der rainy? false)

(if rainy?
  "Panama - always rain :-("
  "Sunshine .. beach time!)

(def status 3)

(case status
  0 "all systems working normally"
  1 "night time (no watering)"
  2 "day time watering"
  3 "day time (no watering)"
  "what the fuck")

; exercise 1

Write a function that takes parameter l
For the valve and that gives back if the
Valve is disabled or not. Level 0 = disabled.
All other levels are enabled.


; exercise 2 .. design a nice display
To report config.

; exercise 3 write a function that gets run
Once a minute to decide if to open a valve or
Not in the current minute.

; exercise 4

Write a function that manages all valve closing/openings.




