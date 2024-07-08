package com.gonzalo.taskapi.endpoints.pendingtask;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task/pending")
public class PendingTaskController implements PendingTaskEndpoint {

	private final PendingTaskDelegate delegate;

	PendingTaskController(PendingTaskDelegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public PendingTaskDelegate getDelegate() {
		return delegate;
	}

}
