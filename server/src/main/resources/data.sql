insert into movie(name, description, release_date) values
    ('The Shawshank Redemption', 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.', parsedatetime('1994-09-10', 'yyyy-MM-dd')),
    ('The Green Mile', 'The lives of guards on Death Row are affected by one of their charges: a black man accused of child murder and rape, yet who has a mysterious gift.', parsedatetime('1999-12-06', 'yyyy-MM-dd')),
    ('The Lord of the Rings: The Return of the King', 'Gandalf and Aragorn lead the World of Men against Sauron''s army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.', parsedatetime('2003-12-01', 'yyyy-MM-dd')),
    ('The Matrix', 'When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.', parsedatetime('1999-03-31', 'yyyy-MM-dd'));

insert into movie_genre(movie_id, genre) values(1, 'DRAMA');

insert into movie_genre(movie_id, genre) values(2, 'DRAMA');
insert into movie_genre(movie_id, genre) values(2, 'FANTASY');

insert into movie_genre(movie_id, genre) values(3, 'ACTION');
insert into movie_genre(movie_id, genre) values(3, 'FANTASY');
insert into movie_genre(movie_id, genre) values(3, 'ADVENTURE');

insert into movie_genre(movie_id, genre) values(4, 'ACTION');
insert into movie_genre(movie_id, genre) values(4, 'SCI_FI');