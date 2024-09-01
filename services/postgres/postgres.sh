#!/bin/bash
 
# Your initialization tasks here
 
# Run sed command to modify the configuration file
# sed -i "/#shared_preload_libraries = ''  # (change requires restart)/c\shared_preload_libraries = 'pg_stat_statements'  # (change requires restart)" /var/lib/postgresql/data/my_data/postgresql.conf
sed -i "/#shared_preload_libraries = ''/c\shared_preload_libraries = 'pg_stat_statements'" /var/lib/postgresql/data/my_data/postgresql.conf