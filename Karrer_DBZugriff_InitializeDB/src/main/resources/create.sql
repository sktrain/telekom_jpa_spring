CREATE TABLE jobs
    ( job_id         VARCHAR(10)
    , job_title      VARCHAR(35)  NOT NULL
    , min_salary     DECIMAL(6)
    , max_salary     DECIMAL(6)
    , PRIMARY KEY(job_id)
    ) ;