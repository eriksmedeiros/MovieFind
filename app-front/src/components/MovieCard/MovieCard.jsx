import "./MovieCard.css";

// eslint-disable-next-line react/prop-types
function MovieCard({imageUrl, title, genre}) {
    return (
        <div className="movieCard">
            <img src={imageUrl} alt={title} />
            <h2>{title}</h2>
            <p>{genre}</p>
        </div>
    );
}

export default MovieCard