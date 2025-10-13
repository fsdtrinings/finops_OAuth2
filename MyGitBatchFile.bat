@echo off
cls

echo ==============================================
echo         GIT AUTOMATION SCRIPT
echo ==============================================

REM --- Get commit message from user ---
set /p cm=Enter the Commit Comment: 

REM --- Get Git remote URL from user ---
set /p url=Enter the Git Location URL: 

echo.
echo ===== Initializing Git Repository =====
if not exist ".git" (
    git init
)

echo.
echo ===== Current Git Status =====
git status

echo.
echo ===== Adding all changes =====
git add -A

echo.
echo ===== Committing changes =====
git commit -m "%cm%"

echo.
echo ===== Status after commit =====
git status

echo.
echo ===== Setting remote URL =====
git remote remove origin 2>nul
git remote add origin "%url%"

echo.
echo ===== Pushing to remote (master) =====
git push -u origin master

echo.
echo ===== DONE =====
pause
