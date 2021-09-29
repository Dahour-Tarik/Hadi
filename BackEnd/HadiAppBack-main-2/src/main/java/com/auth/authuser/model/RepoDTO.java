package com.auth.authuser.model;

public class RepoDTO {

	private Long parentId;
	private Repo repo;
	
	public RepoDTO() {
		// TODO Auto-generated constructor stub
	}

	public RepoDTO(Long parentId, Repo repo) {
		super();
		this.parentId = parentId;
		this.repo = repo;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Repo getRepo() {
		return repo;
	}

	public void setRepo(Repo repo) {
		this.repo = repo;
	}
	
	
}
