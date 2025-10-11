@echo off
REM Minimal launcher: will try to run gradle if available
where gradle >nul 2>nul
if %errorlevel%==0 (
  gradle %*
) else (
  echo "Gradle not found on PATH. Install Gradle or run with a local gradle wrapper."
  exit /b 1
)
