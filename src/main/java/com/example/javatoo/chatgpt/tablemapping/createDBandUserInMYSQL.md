To create a new database with user permissions in MySQL, you need to follow these steps. I'll guide you through creating a new database named "mydatabase" with a user named "myuser" and assigning appropriate permissions:

1. **Log in to MySQL:**

Open a terminal or command prompt and log in to MySQL as a user with administrative privileges (usually the `root` user):

```bash
mysql -u root -p
```

You will be prompted to enter the password for the `root` user.

2. **Create a New Database:**

Create a new database named "mydatabase":

```sql
CREATE DATABASE mydatabase;
```

3. **Create a New User:**

Create a new MySQL user named "myuser" with a password:

```sql
CREATE USER 'myuser'@'localhost' IDENTIFIED BY 'mypassword';
```

Replace `'mypassword'` with the actual password you want to use for the user.

4. **Grant Privileges:**

Next, grant the necessary privileges to the new user for the "mydatabase" database:

```sql
GRANT ALL PRIVILEGES ON mydatabase.* TO 'myuser'@'localhost';
```

This grants the user all privileges (such as SELECT, INSERT, UPDATE, DELETE, etc.) on all tables in the "mydatabase" database.

5. **Flush Privileges:**

After granting privileges, flush the privileges to apply the changes:

```sql
FLUSH PRIVILEGES;
```

6. **Exit MySQL:**

Exit the MySQL console:

```sql
EXIT;
```

Now you've created a new database named "mydatabase" and a user named "myuser" with appropriate permissions to access and manipulate the database. You can use the following command to log in to the MySQL console with the newly created user:

```bash
mysql -u myuser -p
```

After entering the password, you'll be connected to the MySQL console with the user's privileges.

Please note that granting `ALL PRIVILEGES` might not be appropriate for all scenarios. In a production environment, it's recommended to grant only the specific privileges needed for your application's functionality to enhance security.