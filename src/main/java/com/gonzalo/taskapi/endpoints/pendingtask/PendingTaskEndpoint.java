package com.gonzalo.taskapi.endpoints.pendingtask;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.gonzalo.taskapi.endpoints.pendingtask.dto.AddChangePendingInDTO;
import com.gonzalo.taskapi.endpoints.pendingtask.dto.AddInfoPendingInDTO;
import com.gonzalo.taskapi.endpoints.pendingtask.dto.CreatePendingInDTO;
import com.gonzalo.taskapi.service.dto.UserServOutDTO;
import com.gonzalo.taskapi.service.task.pending.dto.PendingOutPageServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.PendingOutServDTO;

import jakarta.validation.Valid;

@Validated
public interface PendingTaskEndpoint {

	default PendingTaskDelegate getDelegate() {
		return new PendingTaskDelegate();
	}

	@GetMapping("/{id}")
	default ResponseEntity<PendingOutServDTO> getPending(@PathVariable int id) {
		return getDelegate().getPendingTask(id);
	}

	@GetMapping("/creator/{id}")
	default ResponseEntity<UserServOutDTO> getCreatorPending(@PathVariable int id) {
		return getDelegate().getCreatorPending(id);
	}

	@GetMapping("/list")
	default ResponseEntity<List<PendingOutServDTO>> getAllPending() {
		return getDelegate().getAllPending();
	}

	@GetMapping("/list/{id}")
	default ResponseEntity<List<PendingOutServDTO>> getAllByCreatorPending(@PathVariable int id) {
		return getDelegate().getAllPendingByCreator(id);
	}

	@GetMapping("/page")
	default ResponseEntity<PendingOutPageServDTO> getAllPagePending(@RequestParam(name = "pageSize") Integer pageSize,
			@RequestParam(name = "pageNumber") Integer pageNumber, @RequestParam(name = "sort") String sort,
			@RequestParam(name = "sortField") String sortField) {
		return getDelegate().getAllPagePending(pageSize, pageNumber, sort, sortField);
	}

	@GetMapping("/page/creator")
	default ResponseEntity<PendingOutPageServDTO> getAllPagePendingByCreator(
			@RequestParam(name = "pageSize") Integer pageSize, @RequestParam(name = "pageNumber") Integer pageNumber,
			@RequestParam(name = "sort") String sort, @RequestParam(name = "sortField") String sortField,
			@RequestParam(name = "idCreator") Integer idCreator) {
		return getDelegate().getAllPagePendingByCreator(pageSize, pageNumber, sort, sortField, idCreator);
	}

	@GetMapping("/page/assigned")
	default ResponseEntity<PendingOutPageServDTO> getAllPagePendingByAssigned(
			@RequestParam(name = "pageSize") Integer pageSize, @RequestParam(name = "pageNumber") Integer pageNumber,
			@RequestParam(name = "sort") String sort, @RequestParam(name = "sortField") String sortField,
			@RequestParam(name = "idAssigned") Integer idAssigned) {
		return getDelegate().getAllPagePendingByAssigned(pageSize, pageNumber, sort, sortField, idAssigned);
	}

	@PostMapping("/create")
	default ResponseEntity<Void> createPending(@Valid @RequestBody CreatePendingInDTO inObj, BindingResult result) {
		return getDelegate().createPending(inObj);
	}

	@PatchMapping("/add/change")
	default ResponseEntity<Void> addChangePending(@Valid @RequestBody AddChangePendingInDTO inObj,
			BindingResult result) {
		return getDelegate().addChangePending(inObj);
	}

	@PatchMapping("/add/info")
	default ResponseEntity<Void> addInfoPending(@Valid @RequestBody AddInfoPendingInDTO inObj, BindingResult result) {
		return getDelegate().addInfoPending(inObj);
	}

}
