java -jar C:\Cucumber\cucumber-sandwich-4.8.1-jar-with-dependencies.jar -n -f Report/ -o Report/
cd Report


for /F "tokens=2" %%i in ('date /t') do set mydate=%%i
set mytime=%time%
set mydate1=%mydate:/=%
set mytime1=%mytime:.=%
set mytime1=%mytimel::=%
set datetime=%mydate1%%mytimel%
set datetimel=%datetime: =%

rename cucumber-html-reports cucumber-html-reports_%datetime1%

