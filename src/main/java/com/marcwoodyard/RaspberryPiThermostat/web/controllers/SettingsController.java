package com.marcwoodyard.RaspberryPiThermostat.web.controllers;

import com.marcwoodyard.RaspberryPiThermostat.peripherals.RaspiStill;
import com.marcwoodyard.RaspberryPiThermostat.peripherals.RelaySwitch;
import com.marcwoodyard.RaspberryPiThermostat.utils.Logger;
import com.marcwoodyard.RaspberryPiThermostat.utils.ProgramSettings;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileInputStream;

@Controller
public class SettingsController {

    @GetMapping("/settings")
    public String settingsForm(Model model) {
        model.addAttribute("programSettings", new ProgramSettings());
        model.addAttribute("logData", Logger.getLogs());
        model.addAttribute("acStatus", RelaySwitch.isAcStatus());

        return "settings";
    }

    @RequestMapping(value = "/settings", params = "programSettings", method = RequestMethod.POST)
    public String submitSettingsForm(@Valid @ModelAttribute("programSettings") ProgramSettings programSettings, BindingResult result) {
        if (result.hasErrors())
            return "redirect:/settings#error";

        return "redirect:/settings#success";
    }

    @RequestMapping("/api/toggle-on-off")
    public String toggleOnOff(@ModelAttribute("programSettings") ProgramSettings programSettings, BindingResult result) {
        RelaySwitch.toggleAC();

        if (result.hasErrors())
            return "redirect:/settings#error";

        return "redirect:/settings#success";
    }

    @RequestMapping("/api/filter-reset")
    public String filterReset(@ModelAttribute("programSettings") ProgramSettings programSettings, BindingResult result) {
        RelaySwitch.filterReset();

        if (result.hasErrors())
            return "redirect:/settings#error";

        return "redirect:/settings#success";
    }

    @RequestMapping(value = "/api/view-screenshot", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImgAsBytes(final HttpServletResponse response) {
        try {
            String picName = "live.png";
            FileInputStream in = new FileInputStream(picName);
            RaspiStill.takePicture(picName, "png");
            return new ResponseEntity<>(IOUtils.toByteArray(in), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
