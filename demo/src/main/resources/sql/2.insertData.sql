INSERT INTO `employee` (`id`, `firstname`, `lastname`, `salary`)
VALUES (1,'JCarlos', 'Cori', 2000)^

INSERT INTO `phone` (`id`, `type`,`areacode`,`number`,`employee_id`) VALUES (1,'home',5914,4704317,1)^
INSERT INTO `phone` (`id`, `type`,`areacode`,`number`,`employee_id`) VALUES (2,'work',591,71762030,1)^


INSERT INTO `address` (`id`, `addressLine1`)
VALUES (1,'Av. villazon')^

INSERT INTO `users` (`id`, `firstName`,`address_id`)
VALUES (1,'Juan Carlos C. P.',1)^