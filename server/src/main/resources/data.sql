insert into movie(name, release_date) values
    ('The Shawshank Redemption', parsedatetime('1994-09-10', 'yyyy-MM-dd')),
    ('The Green Mile', parsedatetime('1999-12-06', 'yyyy-MM-dd')),
    ('The Lord of the Rings: The Return of the King', parsedatetime('2003-12-01', 'yyyy-MM-dd'));

insert into movie_genre(movie_id, genre) values(1, 'DRAMA');

insert into movie_genre(movie_id, genre) values(2, 'DRAMA');
insert into movie_genre(movie_id, genre) values(2, 'FANTASY');

insert into movie_genre(movie_id, genre) values(3, 'FANTASY');
insert into movie_genre(movie_id, genre) values(3, 'ADVENTURE');