/* eslint-disable react/prop-types */
import "./MovieCard.css";

import { FaStar } from "react-icons/fa";

// eslint-disable-next-line react/prop-types
function MovieCard({poster_path, title, vote_average}) {
    return (
        <div className="movieCard">
            <img src={`https://image.tmdb.org/t/p/w500${poster_path}`} alt={title} />
            <h2>{title}</h2>
            <p>
                <FaStar />
                {vote_average.toFixed(1)}
            </p>
            <a href={`/movie/${title}`}>
                <button>Detalhes</button>
            </a>
        </div>
    );
}

export default MovieCard