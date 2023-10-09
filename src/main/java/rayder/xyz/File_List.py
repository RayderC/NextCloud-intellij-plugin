import requests

# Nextcloud server URL and credentials
nextcloud_url = "https://nc.rayder.xyz"
username = "Rayder"
password = "Rayder0817"

# Authenticate to Nextcloud
session = requests.Session()
session.auth = (username, password)

# List files in a specific directory
directory_path = "/Vault/"

# Send a GET request to list files
response = session.get(f"{nextcloud_url}/remote.php/dav/files/{username}{directory_path}")

# Check if the request was successful
if response.status_code == 200:
    # Parse the XML response to get file names
    from xml.etree import ElementTree
    root = ElementTree.fromstring(response.content)
    files = [element.findtext("{http://dav.nextcloud.com/ns}href") for element in root.findall(".//{http://dav.nextcloud.com/ns}response")]

    # Extract file names from the href URLs
    file_names = [file.split("/")[-1] for file in files]

    # Print the list of file names
    for file_name in file_names:
        print(file_name)
else:
    print(f"Failed to list files. Status code: {response.status_code}")


i = input("wait")