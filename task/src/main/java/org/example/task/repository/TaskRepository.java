package org.example.task.repository;

import org.example.task.domen.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = """
            WITH not_done_tasks AS (SELECT task_id
                                    FROM task
                                    WHERE status IS DISTINCT FROM 'DONE')
            UPDATE task_account ta
            SET account_id = (SELECT CASE
                                         WHEN ta.task_id IS NOT DISTINCT FROM ta.task_id
                                             THEN account.account_id
                                         END
                              FROM account
                              ORDER BY random()
                              LIMIT 1)
            WHERE ta.task_id IN (SELECT task_id
                                 FROM not_done_tasks);

            """, nativeQuery = true)
    void shuffle();
}
