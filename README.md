# Campus Smart-Parking Java Implementation Kit

Start with `IMPLEMENTATION_STEPS.md`. JDK 17+ required.

PowerShell compile/run:
```powershell
New-Item -ItemType Directory -Force out | Out-Null
$files = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $files
java -cp out parking.app.Main
java -cp out parking.test.ParkingSelfTest
```

See `docs/tasks/` for all twelve task-specific explanations and evidence requirements. Add your own screenshots and actual outputs to `evidence/`.
