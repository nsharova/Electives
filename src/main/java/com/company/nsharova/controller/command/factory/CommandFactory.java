package com.company.nsharova.controller.command.factory;

import com.company.nsharova.controller.command.Command;
import com.company.nsharova.controller.command.Conversion;
import com.company.nsharova.controller.command.impl.LogOutCommand;
import com.company.nsharova.controller.command.impl.LoginCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Command> allKnownCommandMap = new HashMap<>();

    static {
        /*
        allKnownCommandMap.put(Conversion.LOGIN, new LoginCommand());
        allKnownCommandMap.put(Conversion.REGISTRATION, new RegistrationCommand());
        allKnownCommandMap.put(Conversion.ENTRANT_MENU, new EntrantPageCommand());
        allKnownCommandMap.put(Conversion.UNIVERSITIES, new UniversityCommand());
        allKnownCommandMap.put(Conversion.FACULTIES, new FacultyCommand());
        allKnownCommandMap.put(Conversion.SPECIALITIES, new SpecialityCommand());
        allKnownCommandMap.put(Conversion.REGISTER_USER_FOR_EXAM, new ExamCommand());
        allKnownCommandMap.put(Conversion.EDUCATION_PROGRAMS, new ProgramCommand());
        allKnownCommandMap.put(Conversion.CHOOSE_SPECIALITY, new ChooseProgramCommand());
        allKnownCommandMap.put(Conversion.RATING, new RatingCommand());
        allKnownCommandMap.put(Conversion.LOGOUT, new LogOutCommand());

        allKnownCommandMap.put(Conversion.ADMIN_MENU, new AdminPageCommand());
        allKnownCommandMap.put(Conversion.ADMIN_FACULTIES, new AdminFacultyCommand());
        allKnownCommandMap.put(Conversion.ADMIN_SPECIALITIES, new AdminSpecialityCommand());
        allKnownCommandMap.put(Conversion.ENTRANTS_FROM_EXAM, new EntrantsFromExamCommand());
        allKnownCommandMap.put(Conversion.EDIT_ENTRANT, new EditEntrantCommand());
         */

    }


    public static Command getCommand(String url)  {
        Command command = allKnownCommandMap.get(url);

        return command;
    }
}
