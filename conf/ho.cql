create keyspace if not exists ho
with replication = {
	'class' : 'SimpleStrategy', 'replication_factor' : 1
};

/*
 * user and stats
 */
create table if not exists ho.user (
	login text,
	firstname text,
	lastname text,
	email text,
	password text,
	PRIMARY KEY (login)
);

create table if not exists ho.user_stats (
	login text,
	reputation counter,
	PRIMARY KEY (login)
);


/*
 * question
 */
create table if not exists ho.question (
	id timeuuid,
	authorlogin text,
	title text,
	text text,
	lastupdated timestamp,
	primary key (id)
);

create table if not exists ho.question_stats (
	questionid timeuuid,
	votes counter,
	answers counter,
	views counter,
	primary key (questionid)
);

/*
 * answer
 */
create table if not exists ho.answer (
	questionid timeuuid,
	answerid timeuuid,
	answer text,
	authorlogin text,
	primary key (questionid, answerid)
);

create table if not exists ho.answer_stats (
	questionid timeuuid,
	answerid timeuuid,
	votes counter,
	primary key (questionid, answerid)
);
