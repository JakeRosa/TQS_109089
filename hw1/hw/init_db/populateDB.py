import datetime
import random
import time
from datetime import datetime, timedelta

import psycopg2
from psycopg2 import OperationalError

conn = None

while not conn:
    try:
        conn = psycopg2.connect(
            host="db_pg",
            port="5432",
            user="postgres",
            password="postgres",
            database="busbooking"
        )
        print("Database connected successfully!")
    except OperationalError as e:
        print(e)
        time.sleep(3)

cursor = conn.cursor()

drop_tables_query = '''
DROP TABLE IF EXISTS reservations CASCADE;
DROP TABLE IF EXISTS trips CASCADE;
'''

create_trips_table_query = '''
CREATE TABLE IF NOT EXISTS trips (
    id SERIAL PRIMARY KEY,
    origin VARCHAR(64) NOT NULL,
    destination VARCHAR(64) NOT NULL,
    date DATE NOT NULL,
    departure_time TIME NOT NULL,
    arrival_time TIME NOT NULL,
    duration VARCHAR(64) NOT NULL,
    price_euro DECIMAL(5, 2) NOT NULL,
    bus_id VARCHAR(64) NOT NULL,
    available_seats INT NOT NULL
    )
    '''

create_reservations_table_query = '''
CREATE TABLE IF NOT EXISTS reservations (
    id VARCHAR(64) PRIMARY KEY,
    trip_id INT NOT NULL,
    passenger_name VARCHAR(64) NOT NULL,
    passenger_email VARCHAR(64) NOT NULL,
    passenger_phone VARCHAR(64) NOT NULL,
    reservation_date DATE NOT NULL,
    payment_method VARCHAR(64) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    final_price DECIMAL(5, 2) NOT NULL,
    FOREIGN KEY (trip_id) REFERENCES trips(id)
    )
    '''

cursor.execute(drop_tables_query)
cursor.execute(create_trips_table_query)
cursor.execute(create_reservations_table_query)

# Generate random dates
def generate_random_dates(start_date, end_date, num_dates):
    dates = []
    for _ in range(num_dates):
        random_date = start_date + timedelta(days=random.randint(0, (end_date - start_date).days))
        dates.append(random_date)
    return dates

# Cities
cidades = ['Lisboa', 'Porto', 'Braga', 'Aveiro', 'Coimbra', 'Faro']

# Populate trips table
start_date = datetime.now() + timedelta(days=1) 
end_date = start_date + timedelta(days=2)  

random_dates = generate_random_dates(start_date, end_date, 30)

for date in random_dates:
    origin = random.choice(cidades)
    destination = random.choice(cidades)

    while destination == origin: 
        destination = random.choice(cidades)

    departure_time = datetime.strptime(random.choice(['08:00', '10:00', '12:00', '14:00', '16:00']), '%H:%M').time()
    arrival_time = (datetime.combine(date, departure_time) + timedelta(hours=random.randint(3, 6))).time()
    
    duration_seconds = (datetime.combine(date, arrival_time) - datetime.combine(date, departure_time)).total_seconds()
    duration = str(timedelta(seconds=duration_seconds))
    
    price = round(random.uniform(5, 25), 2)
    
    bus_id = 'BUS' + str(random.randint(1000, 9999))
    
    available_seats = random.randint(10, 50)

    insert_query = '''
    INSERT INTO trips (origin, destination, date, departure_time, arrival_time, duration, price_euro, bus_id, available_seats)
    VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)
    '''
    cursor.execute(insert_query, (origin, destination, date, departure_time, arrival_time, duration, price, bus_id, available_seats))

insert_query = '''
    INSERT INTO trips (origin, destination, date, departure_time, arrival_time, duration, price_euro, bus_id, available_seats)
    VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)
    '''
cursor.execute(insert_query, ("Lisboa", "Porto", datetime.strptime('2024-05-05', '%Y-%m-%d'), datetime.strptime('08:00', '%H:%M').time(), datetime.strptime('12:00', '%H:%M').time(), "4h", 20.0, "BUS007", 50))

conn.commit()

cursor.close()
conn.close()

