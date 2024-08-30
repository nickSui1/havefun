package priv.nick.cbs.topgun.service;

import priv.nick.cbs.topgun.model.SetMenu;
import priv.nick.cbs.topgun.dto.setmenu.SetMenuAddDTO;
import priv.nick.cbs.topgun.dto.setmenu.SetMenuInfoDTO;

import java.util.List;

public interface SetMenuService {
    List<SetMenu> getSetMenus();

    Long add(SetMenuAddDTO setMenuAddDTO);

    SetMenuInfoDTO findById(Long id);
}
