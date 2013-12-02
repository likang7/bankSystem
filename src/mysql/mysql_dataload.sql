INSERT INTO Department VALUES('0', 'root');
INSERT INTO Department VALUES('1', 'individual');
INSERT INTO Department VALUES('2', 'enterprise');

INSERT INTO Employee VALUES('root', '123456', 'Administrator', '0', 'root');
INSERT INTO Employee VALUES('supervisor1', '123456', 'Supervisor', '1', 'root');
INSERT INTO Employee VALUES('supervisor2', '123456', 'Supervisor', '2', 'root');
INSERT INTO Employee VALUES('manager1', '123456', 'Manager', '1', 'supervisor1');
INSERT INTO Employee VALUES('operator1', '123456', 'Operator', '1', 'manager1');

INSERT INTO Sequence VALUES(1000, 'all');