provider "azurerm" {
  features {
    
  }
}

resource "azurerm_resource_group" "konvo_app_rg" {
  name = "rg-konvo-dev"
  location = "East US"
}

