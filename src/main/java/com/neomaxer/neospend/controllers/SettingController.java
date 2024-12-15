package com.neomaxer.neospend.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.models.masters.Setting;
import com.neomaxer.neospend.repositories.masters.SettingRepo;
import com.neomaxer.neospend.utils.CopyPropertiesUtil;
import com.turkraft.springfilter.boot.FilterSpecification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/setting")
@Tag(name = "Setting", description = "APIs for App Settings related operations")
public class SettingController {
	@Autowired
	private SettingRepo settingRepo;

	@Value("${app.upiId}")
	private String upiId;

	@GetMapping("/all")
	@Operation(summary = "Get all Settings")
	public ResponseEntity<Page<Setting>> getAll(@RequestParam(defaultValue = "0", value = "pageNo") int pageNo,
			@RequestParam(defaultValue = "25", value = "pageSize") int pageSize,
			@RequestParam(required = false, value = "filter") String filter) {
		Specification<Setting> spec = null;
		if (filter != null) {
			spec = new FilterSpecification<Setting>(filter);
		}
		return ResponseEntity.ok(settingRepo.findAll(spec, PageRequest.of(pageNo, pageSize)));
	}

//	@GetMapping("/global")
//	@Operation(summary = "Get all Settings")
//	public ResponseEntity<Page<Setting>> getGlobal(@RequestParam(defaultValue = "0", value = "pageNo") int pageNo,
//			@RequestParam(defaultValue = "25", value = "pageSize") int pageSize) {
//		return ResponseEntity.ok(settingRepo.findByStoreIsNullAndDistributorIsNull(PageRequest.of(pageNo, pageSize)));
//	}

	@GetMapping("/count")
	@Operation(summary = "Get count of all Settings")
	public ResponseEntity<Integer> getCount() {
		return ResponseEntity.ok(settingRepo.findAll().size());
	}

	@PostMapping("/create")
	@Operation(summary = "Create a new Setting")
	public ResponseEntity<Setting> create(@RequestBody Setting setting) {
		setting.setSettingKey(setting.getSettingKey().toUpperCase().replace(" ", "_"));
		return ResponseEntity.ok(settingRepo.save(setting));
	}

	@GetMapping("/get/{settingId}")
	@Operation(summary = "Get one Setting")
	public ResponseEntity<Setting> getSingle(@PathVariable("settingId") UUID settingId) {
		return ResponseEntity.ok(settingRepo.findById(settingId).get());
	}

//	@GetMapping("/getByKey/{settingKey}")
//	@Operation(summary = "Get one Setting by key")
//	public ResponseEntity<?> getByKey(@PathVariable("settingKey") String settingKey) {
//		Optional<Setting> setting = settingRepo.findFirstBySettingKeyAndStoreIsNullAndDistributorIsNull(settingKey);
//		if (setting.isEmpty()) {
//			return ResponseEntity.ok(new MessageResponse("No Setting found"));
//		} else {
//			return ResponseEntity.ok(setting.get());
//		}
//	}
//
//	@GetMapping("/getByKeyAndStore/{settingKey}/{storeId}")
//	@Operation(summary = "Get one Setting by key")
//	public ResponseEntity<?> getByKeyAndStore(@PathVariable("settingKey") String settingKey,
//			@PathVariable("storeId") UUID storeId) {
//		Optional<Setting> setting = settingRepo.findBySettingKeyAndStoreId(settingKey, storeId);
//		if (setting.isEmpty()) {
//			return ResponseEntity.ok(new MessageResponse("No Setting found"));
//		} else {
//			return ResponseEntity.ok(setting.get());
//		}
//
//	}

//	@GetMapping("/getByKeyAndDistributor/{settingKey}/{distributorId}")
//	@Operation(summary = "Get one Setting by key and Distributor")
//	public ResponseEntity<?> getByKeyAndDistributor(@PathVariable("settingKey") String settingKey,
//			@PathVariable("distributorId") UUID distributorId) {
//		Optional<Setting> setting = settingRepo.findBySettingKeyAndDistributorId(settingKey, distributorId);
//		if (setting.isEmpty()) {
//			return ResponseEntity.ok(new MessageResponse("No Setting found"));
//		} else {
//			return ResponseEntity.ok(setting.get());
//		}
//	}

	@PutMapping("/update/{settingId}")
	@Operation(summary = "Update a Setting by Id")
	public ResponseEntity<Setting> update(@PathVariable("settingId") UUID settingId, @RequestBody Setting setting) {
		setting.setSettingKey(setting.getSettingKey().toUpperCase().replace(" ", "_"));
		Setting oldsetting = settingRepo.findById(settingId).get();
		CopyPropertiesUtil.copyProperties(setting, oldsetting);
		return ResponseEntity.ok(settingRepo.save(oldsetting));

	}
//
//	@DeleteMapping("/delete/{settingId}")
//	@Operation(summary = "Delete a Setting by Id")
//	public ResponseEntity<?> delete(@PathVariable("settingId") UUID settingId) {
//		settingRepo.deleteById(settingId);
//		return ResponseEntity.ok().build();
//	}

//	@GetMapping("/getUpiUrl")
//	@Operation(summary = "Get one Setting")
//	public String getUpiUrl(@RequestParam("amount") String amount, @RequestParam("tn") String transactionType,
//			@RequestParam("storeId") Optional<UUID> storeId,
//			@RequestParam("distributorId") Optional<UUID> distributorId, @RequestParam("pn") String pn,
//			@RequestParam("tranid") String tranid) throws JsonMappingException, JsonProcessingException {
//		Setting setting;
//		String upiID = "";
//		String merchantID = "";
//		// TODO get the customer's upi id from setting value convert to json and extract
//		if (storeId.isPresent()) {
//			setting = settingRepo.findBySettingKeyAndStoreId("PAYMENT_METHODS", storeId.get()).get();
//		} else if (distributorId != null) {
//			setting = settingRepo.findBySettingKeyAndDistributorId("PAYMENT_METHODS", distributorId.get()).get();
//		} else {
//			throw new GenericException("Send either store or distributor id");
//		}
//		ObjectMapper objectMapper = new ObjectMapper();
//		PaymentMethods methods = objectMapper.readValue(setting.getSettingValue(), PaymentMethods.class);

	/*
	 * for(PaymentMethod method: methods.getMethods()) {
	 * if(method.getPaymentMethod().equals("UPI")) { for(Prop prop:
	 * method.getFields()) { if(prop.getLabel().equals("enabled") &&
	 * prop.getVal().equals("true")) { for(Prop prp: method.getFields()) {
	 * if(prp.getLabel().equals("upiID")) { upiID = prp.getVal(); }
	 * if(prp.getLabel().equals("merchantID")) { merchantID=prp.getVal(); } } } } }
	 * }
	 */

//		for (PaymentMethod method : methods.getMethods()) {
//			if (method.getPaymentMethod().equals("UPI")) {
//				for (Prop prop : method.getProps()) {
//					if (prop.getKey().equals("enabled") && prop.getVal().equals("true")) {
//						for (Prop period : method.getProps()) {
//							if (period.getKey().equals("upiID")) {
//								upiID = period.getVal();
//							}
//							if (period.getKey().equals("merchantID")) {
//								merchantID = period.getVal();
//							}
//						}
//					}
//				}
//			}
//		}
//
//		if (upiID.equals("")) {
//			throw new GenericException("Could not find UPI ID" + methods.toString());
//		}
//
//		String upiUrl = "upi://pay?pa=" + upiID + "&pn=" + pn + "&am=" + amount + "&tn=" + transactionType
//				+ "&cu=INR&mode=00" + "&mc=" + merchantID + "&tr=" + tranid;
//		return upiUrl;
//	}

	@GetMapping("/getSplentaUpiUrl")
	@Operation(summary = "Get one Setting")
	public String getSplentaUpiUrl(@RequestParam("amount") String amount, @RequestParam("tn") String transactionType) {
		String upiUrl = "upi://pay?pa=" + upiId + "&pn=Neomaxer Fintech Private Limited&am=" + amount + "&tn="
				+ transactionType;
		return upiUrl;
	}
}
