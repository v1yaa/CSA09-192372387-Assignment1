# Pseudocode
## Allocate
```text
FOR each floor f
 FOR each slot s in floor f
  IF slot is free
   store registration in slot
   RETURN (f,s)
RETURN NO_SLOT_AVAILABLE
```
## Release
```text
IF floor or slot index invalid: RETURN false
IF slot is already free: RETURN false
mark slot free
RETURN true
```
## Exit
```text
find active ticket by registration
reject if no ticket or exit precedes entry
calculate fee using valid pass and vehicle pricing
close ticket; release slot; remove active ticket
return fee
```
