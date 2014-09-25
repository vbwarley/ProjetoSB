package fachada;

import nucleo.model.negocios.Usuario;

public interface IFachada {
	
	void createProfile(String login, String senha, String nome_exibicao, String email, String sexo, String data_nasc, String endereco, String interesses, String quem_sou_eu, String filmes, String musicas, String livros);
	String getProfileInformation(String atributo);
	Long doLogin(String login, String senha);
	boolean isUserLogged(String login);
	Usuario getProfileInformationBySessionId(Long sessionId, String atributo);
	void logoff(Long sessionId);
	void changeProfileInformation(Long sessionId, String atributo, String valor);
	int createBlog(Long sessionId, String titulo, String descricao, String background, boolean autz_comment, boolean autz_comment_annon);
	String getBlogInformation(int id, String atributo);
	void changeBlogInformation(Long sessionId, int id, String atributo, String valor);
	int createPost(Long sessionId, int blogId, String titulo, String texto);
	int attachSound(Long sessionId, int postId, String descricao, String dado);
	int attachMovie(Long sessionId, int postId, String descricao, String dado);
	int attachPicutre(Long sessionId, int postId, String descricao, String dado);
	String getPostInformation(int postId, String atributo);
	int getNumberOfSounds(int postId);
	int getNumberOfMovies(int postId);
	int getNumberOfPictures(int postId);
	int getSound(int postId, int index);
	String getSoundDescription(int audioId);
	String getSoundData(int audioId);
	int getMovie(int postId, int index);
	String getMovieDescription(int movieId);
	String getMovieData(int movieId);
	int getPicture(int postId, int index);
	String getPictureDescription(int imagemId);
	String getPicutreData(int imagemId);
	int getNumberOfBlogsByLogin(String login);
	int getNumberOfBlogsBySessionId(Long sessionId);
	int getNumberOfPosts(int blogId);
	int getPost(int blogId, int index);
	void changePostInformation(Long sessionId, int postId, String atributo, String valor);
	void deleteMovie(Long sessionId, int movieId); 
	void deleteSound(Long sessionId, int soundId);
	void deletePicture(Long sessionId, int pictureId);
	int addComment(Long sessionId, int postId, String texto);
	int getNumberOfCommentsByPost(int postId);
	int getComment(int postId, int index); // retorno int?
	String getCommentText(int idComentario);
	String getCommentAuthor(int idComentario);
	int addCommentAnno(int postId, String texto);
	void deletePost(Long sessionId, int postId);
	int getBlogByLogin(String login, int index);
	void deleteBlog(Long sessionId, int blogId);
	void deleteProfile(Long sessionId);
	String findProfileByName(String match, String order, int offset, int maxentries);
	String findProfileByLogin(String match, String order, int offset, int maxentries);
	String findProfileByEmail(String match);
	String findProfileByDateInterval(String from, String to, String order, int offset, int maxentries);
	int findBlogByName(String match, String order, int offset, int maxentries);
	int findBlogByDescription(String match, String order, int offset, int maxentries);
	void addPostAnnouncements(Long sessionId, int blogId); // quéisso?
	int getNumberOfAnnouncements(Long sessionId);
	int getPostJustCreated(int announcementId);
	int getAnnouncement(Long sessionId, int index);
	void deleteAnnouncement(Long sessionId, int announcementId);
	int addSubComment(Long sessionId, int parentCommentId, String texto);
	String getParentComment(int commentId);
	int getParentPost(int commentId);
	int getNumberOfCommentsByBlog(int blogId);
	int getTotalNumberOfCommentsByPost(int postId);
	int getNumberOfCommentsByComment(int commentId);
	int getTotalNumberOfCommentsByComment(int commentId); // pq existe?
	int getSubComent(int commentId, int index);
	
}
