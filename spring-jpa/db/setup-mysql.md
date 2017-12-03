Create data directory in mysql folder
<br>
Create a file my.ini where mysql extracted
<pre>
[mysqld]
# set basedir to your installation path
basedir=C:\\Dev\\mysql-5.7.20
# set datadir to the location of your data directory
datadir=C:\\Dev\\mysql-5.7.20\\data
</pre>

<hr>
Then run the following commands 
<ul>
<li>mysqld --defaults-file=C:\\Dev\\mysql-5.7.20\\my.ini --console --initialize-insecure --user=mysql1</li>
<li>start mysqld</li>
<li>mysql -u root</li>
<li>mysqladmin -u root shutdown</li>
</ul>

<pre>
ALTER USER 'root'@'localhost' IDENTIFIED BY 'Password1';
GO
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY 'Password1';
</pre>
<hr>
List of all users<br>
SELECT user FROM mysql.user GROUP BY user;
delete user<br>
DELETE FROM mysql.user WHERE user = 'username';