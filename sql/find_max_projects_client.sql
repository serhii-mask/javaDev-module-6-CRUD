-- Запити для пошуку клієнта з найбільшою кількістю проектів
SELECT c.name, COUNT(p.client_id) AS projects_client FROM client c
JOIN project p ON p.client_id = c.id
GROUP BY client_id
HAVING projects_client = (
	SELECT MAX(proj) FROM (
		SELECT COUNT(client_id) AS proj
		FROM project
		GROUP BY client_id
   )
);