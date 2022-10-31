(ns water.init
  (:require
   [water.state]
   [water.display]
   [water.scheduler]
   ))

; Show something in terminal, so we know this code gets executed.
(println "water init!")

; 3 valves are open
(water.state/set-valve-level 0 4)
(water.state/set-valve-level 3 4)
(water.state/set-valve-level 7 2)

; enable the watering system
(water.state/set-enabled true)

; print state once
(water.display/print-state)

; start the valves scheduler
(water.scheduler/start-valve-scheduler)

; start the simulator
; (water.scheduler/start-simulator)