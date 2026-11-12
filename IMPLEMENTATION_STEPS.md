# Detailed Implementation Steps — Campus Smart-Parking System

## 1. Requirements and setup
Install JDK 17 or later. In PowerShell run `java -version` and `javac -version`.
This project uses only core Java; no IDE, Maven, Gradle, database or internet access is required.

## 2. Project structure
`src/parking/model` contains Vehicle, subclasses, Pass types and ParkingTicket.
`src/parking/service` contains ParkingFacility and ParkingService.
`src/parking/pricing` contains the pricing policy.
`src/parking/app` contains the runnable demonstration.
`src/parking/test` contains a no-dependency test program.
`docs/tasks` contains the report plan for all 12 tasks.

## 3. Compile and run (Windows PowerShell)
Open PowerShell in the extracted folder:
```powershell
New-Item -ItemType Directory -Force out | Out-Null
$files = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $files
java -cp out parking.app.Main
java -cp out parking.test.ParkingSelfTest
```
macOS/Linux:
```bash
mkdir -p out
find src -name '*.java' -print0 | xargs -0 javac -d out
java -cp out parking.app.Main
java -cp out parking.test.ParkingSelfTest
```

## 4. Understand the implementation in order
1. Read `Vehicle.java`: abstract class, private final registration number, constructor validation, equals/hashCode.
2. Read `TwoWheeler.java` and `Car.java`: inheritance and overridden rates.
3. Read `Chargeable.java` and `ElectricCar.java`: interface capability and EV charging.
4. Read `EBike.java` and `DeliveryVan.java`: Task 12 vehicle categories.
5. Read `Pass.java`, `MonthlyPass.java`, `OneDayPass.java`: interface-based pass validity.
6. Read `ParkingFacility.java`: jagged array; `null` represents a free slot; allocate scans floor then slot; release validates indexes.
7. Read `ParkingTicket.java`: ticket ID, vehicle, slot coordinates and entry/exit lifecycle.
8. Read `PricingService.java`: started-hour calculation, EV charging, e-bike flat fee and delivery van daytime pricing.
9. Read `ParkingService.java`: entry/exit orchestration and HashMap active ticket index.
10. Run `Main.java`, then `ParkingSelfTest.java`.

## 5. Pricing assumptions
- Two-wheeler: Rs 10 per started hour.
- Car: Rs 30 per started hour.
- Every started hour is charged in full for standard vehicles: `(minutes + 59) / 60`. Thus 61 minutes = 2 hours and 130 minutes = 3 hours.
- EV charging: Rs 12/kWh, added to parking fee.
- E-bike: Rs 15 flat per visit.
- Delivery van: double car rate for time in 09:00 inclusive to 17:00 exclusive. Time is minute-prorated across the daytime boundary: Rs 0.50/min outside and Rs 1/min inside.
- A valid pass waives the entire amount in this sample. Monthly pass applies to student/staff in the configured month; one-day pass requires entry and exit on its valid date.
If your instructor specifies another policy, change it consistently and explain it in the report.

## 6. Task-by-task implementation and report
See `docs/tasks/Task01.md` ... `Task12.md`. The assignment asks for analysis and alternative-concept Big-O comparisons in addition to source code. For each task, write the explanation in your own words, link it to the relevant source file, and include output evidence where requested.

## 7. Save your own evidence
After running tests:
```powershell
java -cp out parking.test.ParkingSelfTest | Tee-Object evidence-self-test.txt
java -cp out parking.app.Main | Tee-Object evidence-demo.txt
```
Capture screenshots of the terminal output and add them to the `evidence` folder. This kit does not fabricate screenshots or claim execution on your laptop.

## 8. Complexity summary
- First-free-slot allocation: O(S) worst case for S total slots.
- Release by known floor and slot: O(1).
- Active ticket lookup with HashMap: O(1) average, O(n) worst case.
- ArrayList/linear scan ticket lookup: O(n).
- Occupancy storage: O(S); active ticket index: O(n).

## 9. GitHub
Create repository `CSA09-<RegisterNumber>-Assignment1`. Then:
```powershell
git init
git add .
git commit -m "Implement smart parking core"
git branch -M main
git remote add origin https://github.com/<username>/CSA09-<RegisterNumber>-Assignment1.git
git push -u origin main
```
Replace placeholders. Commit progressively, ideally once for each task. Do not claim it is pushed until you have completed the push and verified the repository.

## 10. Before submission
- Add name, register number, date, JDK version and GitHub URL.
- Complete each Task01–Task12 write-up.
- Include UML, pseudocode, actual test table, screenshots and hand-calculated daily total.
- Execute both code-completion programs from the assignment exactly as supplied; preserve their main methods and expected output.
- Be prepared to explain every class, OOP concept, fee rule and test.
