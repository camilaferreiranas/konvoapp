provider "azurerm" {
  features {
    
  }
}

resource "azurerm_resource_group" "konvo_app_rg" {
  name = "rg-konvo-dev"
  location = "East US"
}

resource "azurerm_virtual_network" "vnet" {
  name = "vnet-dev"
   address_space = ["10.0.0.0/16"]
  location = azurerm_resource_group.konvo_app_rg.location
  resource_group_name = azurerm_resource_group.konvo_app_rg.name
}

resource "azurerm_subnet" "subnet" {
  name = "subnet-dev"
  resource_group_name = azurerm_resource_group.konvo_app_rg.name
  virtual_network_name = azurerm_virtual_network.vnet.name
  address_prefixes = ["10.0.1.0/24"]
}

resource "azurerm_network_security_group" "nsg" {
    name = "nsg-dev"
    location = azurerm_resource_group.konvo_app_rg.location
    resource_group_name = azurerm_resource_group.konvo_app_rg.name
    security_rule {
        name = "AllowSSH"
        priority = 1001
        direction = "Inbound"
        access = "Allow"
        protocol = "Tcp"
        source_port_range = "*"
        destination_port_range = "22"
        source_address_prefix = "*"
        destination_address_prefix = "*"
    }

    security_rule {
        name = "AllowMySQL"
        priority = 1002
        direction = "Outbound"
        access = "Allow"
        protocol = "Tcp"
        source_port_range = "*"
        destination_port_range = "3306"
        source_address_prefix = "*"
        destination_address_prefix = "*"
    }
  
}

resource "azurerm_network_interface" "nic" {
  name = "nic-dev"
  location = azurerm_resource_group.konvo_app_rg.location
  resource_group_name = azurerm_resource_group.konvo_app_rg.name
  ip_configuration {
    name = "ipconfig"
    subnet_id = azurerm_subnet.subnet.id
    private_ip_address_allocation = "Dynamic"
  }
}

resource "azurerm_linux_virtual_machine" "vm-dev-konvo" {
  name = "dev-vm"
  resource_group_name = azurerm_resource_group.konvo_app_rg.name
  location = azurerm_resource_group.konvo_app_rg.location
  size = "Standard_B1s"
  admin_username = var.admin_username
  network_interface_ids = [azurerm_network_interface.nic.id]
  disable_password_authentication = false
  admin_password = var.admin_password
  os_disk {
    caching = "ReadWrite"
    storage_account_type = "Standard_LRS"
  }
   source_image_reference {
      publisher = "Canonical"
      offer = "UbuntuServer"
      sku = "22_04-lts"
      version = "latest"
    }
}