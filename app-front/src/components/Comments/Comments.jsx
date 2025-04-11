import { useState, useEffect } from "react";
import "./Comments.css";
import PropTypes from "prop-types";

const Comments = ({ movieId }) => {
  const [comments, setComments] = useState([]);
  const [newComment, setNewComment] = useState("");

  // Buscar comentários ao carregar o componente
  useEffect(() => {
    fetch(`http://localhost:8080/api/movies/${movieId}/comments`)
      .then((res) => res.json())
      .then((data) => setComments(data))
      .catch((err) => console.error("Erro ao buscar comentários:", err));
  }, [movieId]);

  // Adicionar novo comentário
  const handleAddComment = async () => {
    if (!newComment.trim()) return; // Evita comentários vazios

    const commentData = {
      text: newComment,
    };

    try {
      const response = await fetch(
        `http://localhost:8080/api/movies/${movieId}/comments`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(commentData),
        }
      );

      if (!response.ok) throw new Error("Erro ao adicionar comentário");

      const savedComment = await response.json();
      setComments([...comments, savedComment]); // Atualiza a lista de comentários
      setNewComment(""); // Limpa o campo de entrada
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="comments-container">
      <h3>Comentários</h3>

      <ul>
        {comments.map((comment, index) => (
          <li key={index}>{comment.text}</li>
        ))}
      </ul>

      <div className="comment-form">
        <input
          type="text"
          placeholder="Escreva um comentário..."
          value={newComment}
          onChange={(e) => setNewComment(e.target.value)}
        />
        <button onClick={handleAddComment}>Enviar</button>
      </div>
    </div>
  );
};
Comments.propTypes = {
  movieId: PropTypes.string.isRequired,
};

export default Comments;