package com.springboot.thymeleafdemo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.springboot.thymeleafdemo.entity.Employee;
import com.springboot.thymeleafdemo.entity.LeaveDays;
import com.springboot.thymeleafdemo.service.EmployeeService;
import com.springboot.thymeleafdemo.service.LeaveDaysService;

@Controller
@RequestMapping("/leavedays")
public class LeaveDaysController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty String to
		// null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	private LeaveDaysService leaveDaysService;
	private EmployeeService employeeService;

	public LeaveDaysController(LeaveDaysService leaveDaysService, EmployeeService employeeService) {
		this.leaveDaysService = leaveDaysService;
		this.employeeService = employeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String lisLeaveDays(Model theModel) {

		// get leave days from db
		java.util.List<LeaveDays> theLeaveDays = leaveDaysService.findAll();

		// add to the spring model
		theModel.addAttribute("leavedays", theLeaveDays);

		return "leavedays/list-leavedays";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Employee theEmployee = new Employee();

		// create model attribute to bind form data
		LeaveDays theLeaveDays = new LeaveDays();

		theModel.addAttribute("leavedays", theLeaveDays);

		theModel.addAttribute("employee", theEmployee);

		return "leavedays/leavedays-form";

	}

	@GetMapping("/showFormForAdd2")
	public String showFormForAdd2(Model theModel) {

		String username;
		Employee theEmployee = new Employee();

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		try {
			// find the employee with username from actual session
			theEmployee = employeeService.findByLogin(username);

		} catch (NullPointerException e) {

		}

		// create model attribute to bind form data
		LeaveDays theLeaveDays = new LeaveDays();

		// set the ID actual logged user
		theLeaveDays.setEmployee(theEmployee);

		theModel.addAttribute("employee", theEmployee);

		theModel.addAttribute("leavedays", theLeaveDays);

		return "leavedays/leavedays-form2";

	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("leavedaysId") int theId, Model theModel) {

		// get the leave days form the service
		LeaveDays theLeaveDays = leaveDaysService.findById(theId);

		// set leave day as a model attribute to pre-populate the form
		theModel.addAttribute("leavedays", theLeaveDays);

		// send over to our form
		return "leavedays/leavedays-form";
	}

	@PostMapping("/save")
	public String saveLeaveDays(@ModelAttribute("leavedays") LeaveDays theLeaveDays) {

		// save the leave day
		leaveDaysService.save(theLeaveDays);

		Employee employee = new Employee();

		// get employee by id from db
		employee = employeeService.findById(theLeaveDays.getEmployee().getId());

		// get your leave days from employee
		int yourLeaveDays = employee.getYourLeaveDays();

		// update your leave days
		employee.setYourLeaveDays(yourLeaveDays - theLeaveDays.getLeaveDays());

		// save employee
		employeeService.save(employee);

		// use a redirect to prevent duplicate submissions
		return "redirect:/";

	}

	@GetMapping("/delete")
	public String delete(@RequestParam("leavedaysId") int theId) {

		Employee employee = new Employee();

		// get employee by id from db
		employee = employeeService.findById(leaveDaysService.findById(theId).getEmployee().getId());

		// get your leave days from employee
		int yourLeaveDays = employee.getYourLeaveDays();

		// update your leave days
		employee.setYourLeaveDays(yourLeaveDays + leaveDaysService.findById(theId).getLeaveDays());

		// delete the leave day
		leaveDaysService.deleteById(theId);

		// use a redirect to /employees/list
		return "redirect:/";

	}

	@GetMapping("/print")
	public String print(@RequestParam("leavedaysId") int theId) throws FileNotFoundException, DocumentException {

		LeaveDays leaveDays = leaveDaysService.findById(theId);

		Date date = leaveDaysService.findById(theId).getDateFrom();
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("purposal.pdf"));
		document.open();

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		table.addCell(getCell(
				employeeService.findById(leaveDays.getEmployee().getId()).getFirstName() + " "
						+ employeeService.findById(leaveDays.getEmployee().getId()).getLastName(),
				PdfPCell.ALIGN_LEFT));
		table.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		table.addCell(getCell("Kobylin, dnia: " + today.format(formatter), PdfPCell.ALIGN_RIGHT));
		document.add(table);

		document.add(new Phrase("\n"));

		PdfPTable table4 = new PdfPTable(3);
		table4.setWidthPercentage(100);
		table4.addCell(getCell("", PdfPCell.ALIGN_LEFT));
		table4.addCell(getCell("WNIOSEK O URLOP", PdfPCell.ALIGN_CENTER));
		table4.addCell(getCell("", PdfPCell.ALIGN_RIGHT));
		document.add(table4);

		document.add(new Phrase("\n"));
		document.add(new Phrase("\n"));

		PdfPTable table2 = new PdfPTable(3);
		table2.setWidthPercentage(100);
		table2.addCell(getCell("Prosze o udzielenie: ", PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell("", PdfPCell.ALIGN_RIGHT));
		document.add(table2);

		Paragraph p2 = new Paragraph(
				"Urlopu wypoczynkowego / bezpłatngo / okolicznościowego / opieki nad dzieckiem/ * w okresie od: ");
		document.add(p2);
		Paragraph p3 = new Paragraph("dnia: " + leaveDaysService.findById(theId).getDateFrom() + " do dnia: "
				+ leaveDaysService.findById(theId).getDateTo() + " wlacznie tj. "
				+ leaveDaysService.findById(theId).getLeaveDays() + " dni roboczych, za rok "
				+ leaveDaysService.findById(theId).getYear());
		document.add(p3);

		document.add(new Phrase("\n"));
		document.add(new Phrase("\n"));
		document.add(new Phrase("\n"));

		PdfPTable table5 = new PdfPTable(3);
		table5.setWidthPercentage(100);
		table5.addCell(getCell("...............................", PdfPCell.ALIGN_LEFT));
		table5.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		table5.addCell(getCell("...............................", PdfPCell.ALIGN_RIGHT));
		document.add(table5);

		PdfPTable table6 = new PdfPTable(3);
		table6.setWidthPercentage(100);
		table6.addCell(getCell("      podpis szefa", PdfPCell.ALIGN_LEFT));
		table6.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		table6.addCell(getCell("podpis pracownika", PdfPCell.ALIGN_RIGHT));
		document.add(table6);

		document.add(new Phrase("\n"));

		PdfPTable table7 = new PdfPTable(3);
		table7.setWidthPercentage(100);
		table7.addCell(getCell("", PdfPCell.ALIGN_LEFT));
		table7.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		table7.addCell(getCell("* niepotrzebne skreslic", PdfPCell.ALIGN_RIGHT));
		document.add(table7);

		// Start a new page
		document.newPage();

		document.close();

		return "redirect:/";

	}

	@RequestMapping(path = "/download", method = RequestMethod.GET)
	public ResponseEntity<Resource> download() throws IOException {
		File file = new File("C:\\Users\\KP-Karton\\eclipse-workspace\\count-my-leave-days\\purposal.pdf");

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=purposal.pdf");
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");

		Path path = Paths.get(file.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

		return ResponseEntity.ok().headers(header).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
	}

	private static PdfPCell getCell(String text, int alignment) {
		PdfPCell cell = new PdfPCell(new Phrase(text));
		cell.setPadding(0);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

}
