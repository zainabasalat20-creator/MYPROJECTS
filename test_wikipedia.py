from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

# Chrome options
chrome_options = Options()

# Launch Chrome browser (will open on screen)
driver = webdriver.Chrome(
    service=Service(ChromeDriverManager(driver_version="148").install()),
    options=chrome_options
)

try:
    # Open Wikipedia
    driver.get("https://www.wikipedia.org")

    # Get page title
    actual_title = driver.title
    expected_title = "Wikipedia"

    print(f"Actual Title   : {actual_title}")
    print(f"Expected Title : {expected_title}")

    # Verify title
    if expected_title in actual_title:
        print("Build Success")
    else:
        print("Title Mismatch! Test Failed.")
        exit(1)

finally:
    driver.quit()
    print("Browser closed.")
