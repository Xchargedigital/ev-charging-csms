@echo off
echo ========================================
echo  EV Charging CSMS - GitHub Upload Script
echo ========================================
echo.

echo [1/5] Checking Git status...
git status
echo.

echo [2/5] Configuring remote repository...
git remote remove origin 2>nul
git remote add origin https://github.com/Xchargedigital/ev-charging-csms.git
echo Remote repository configured.
echo.

echo [3/5] Checking remote configuration...
git remote -v
echo.

echo [4/5] Pushing code to GitHub...
echo Note: You may be prompted for GitHub credentials
git push -u origin main
echo.

echo [5/5] Verifying upload...
git status
echo.

echo ========================================
echo  Upload completed! Check your GitHub repository:
echo  https://github.com/Xchargedigital/ev-charging-csms
echo ========================================
pause
