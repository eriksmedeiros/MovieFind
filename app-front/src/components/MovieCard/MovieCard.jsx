import "./MovieCard.css";

// eslint-disable-next-line react/prop-types
function MovieCard({poster_path, title, vote_average}) {
    return (
        <div className="movieCard">
            <img src={`https://image.tmdb.org/t/p/w500${poster_path}`} alt={title} />
            <h2>{title}</h2>
            <p>{vote_average}</p>
        </div>
    );
}

export default MovieCard