package com.mansoft.practice.services.reposiotry;

import java.util.List;

import com.mansoft.practice.services.model.ExceptionHistory;

public interface ExceptionHistoryDao extends
		BaseRepository<ExceptionHistory, Integer> {
	List<ExceptionHistory> findAll();
}
