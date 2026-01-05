# imports
from dotenv import load_dotenv
import os
import mysql.connector

# load envs from Github
load_dotenv()

MYSQL_HOSTNAME = os.getenv("MYSQL_HOSTNAME")
MYSQL_USER = os.getenv("MYSQL_USER")
MYSQL_PASSWORD = os.getenv("MYSQL_PASSWORD")
MYSQL_DATABASE = os.getenv("MYSQL_DATABASE")

if not all([MYSQL_HOSTNAME, MYSQL_USER, MYSQL_PASSWORD, MYSQL_DATABASE]):
    raise RuntimeError(
        "Missing environment variables. "
        "Set MYSQL_* variables before running this notebook."
    )

# create mysql connector
def get_connector():
    return mysql.connector.connect(
    host=MYSQL_HOSTNAME,
    port=3306,
    user=MYSQL_USER,
    password=MYSQL_PASSWORD,
    database=MYSQL_DATABASE)